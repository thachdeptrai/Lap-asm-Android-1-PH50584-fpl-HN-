package com.meomeo.lap5lap6.model;

    public class Student {
        private String branch;
        private String name;
        private String address;

        public Student(String branch, String name, String address) {
            this.branch = branch;
            this.name = name;
            this.address = address;
        }

        public String getBranch() {
            return branch;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

