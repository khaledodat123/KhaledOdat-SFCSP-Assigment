package com.khaledodat.assessment.utils;

public class Enums {
    public enum Gender {
        MALE(1), FEMALE(2);

        private final int value;

        Gender(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }

    }

    public enum ResponseCodes {
        SUCCESS(0), FAILED(-1), UNAUTHORIZED(4), FORCE_UPDATE(5);

        private final long value;

        ResponseCodes(long i) {
            this.value = i;
        }

        public long getValue() {
            return value;
        }

    }

    public enum ScreenSizes {
        PHONE(0), TABLET_7_INCH(1), TABLET_10_INCH(2);
        private final int value;

        ScreenSizes(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

}
