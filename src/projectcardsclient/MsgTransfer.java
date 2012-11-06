/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcardsclient;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author Norrecito
 */
public abstract class MsgTransfer {
  private static abstract class SimpleWorker implements Runnable {

        // begin worker thread control
        private Thread runThread = null;
        private boolean running = false;

        public synchronized void start() {
            if (runThread != null && runThread.isAlive()) {
                throw new IllegalStateException("worker thread is already running.");
            }
            running = true;
            runThread = new Thread(this);
            runThread.start();
        }

        public synchronized void stop() {
            running = false;
            if (runThread != null) {
                runThread.interrupt();
            }
            runThread = null;
        }
        // end worker thread control

        // the queue of things to be written
        private Vector<Token> queue = new Vector<Token>();

        public void submitToken(Token t) {
            queue.add(t);
        }

        // the object output stream.
        // should be set before our thread is started.
        private ObjectOutputStream objectOutput;

        public ObjectOutputStream getObjectOutput() {
            return objectOutput;
        }

        public SimpleWorker() {
        }

        public SimpleWorker(ObjectOutputStream objectOutput) {
            this.objectOutput = objectOutput;
        }

        public void setObjectOutput(ObjectOutputStream objectOutput) {
            this.objectOutput = objectOutput;
        }

        protected abstract void onException(Exception ex);

        @Override
        public void run() {
            while (running) {
                if (queue.size() == 0) {
                    try {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException ex) {
                        // empty
                    }
                    continue;
                }
                Token aToken = queue.remove(0);
                try {
                    objectOutput.writeObject(aToken.outputMsg);
                    objectOutput.flush();
                }
                catch (IOException e) {
                    onException(e);
                }
                // notify the thread that submitted this token we are done with it.
                synchronized(aToken) {
                    aToken.notify();
                }
            } // while
        }

    }

 private static class Token {

        Object outputMsg;

        public Object getOutputMsg() {
            return outputMsg;
        }

        public void setOutputMsg(Object outputMsg) {
            this.outputMsg = outputMsg;
        }

    }  
}


