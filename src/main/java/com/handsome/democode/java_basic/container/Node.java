package com.handsome.democode.java_basic.container;

class Node{
        int i;
        String id;

        public Node(int i, String id){
            this.i = i;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    ", id='" + id + '\'' +
                    '}';
        }
    }