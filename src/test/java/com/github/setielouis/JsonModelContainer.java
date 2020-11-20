package com.github.setielouis;

import lombok.Data;

/**
 * This sub class is used only to test finality
 */

public class JsonModelContainer {

        @Data
        public static class  ModelOne{
                private String attr1;
                private String attr2;
                private String attr3;
        }

        @Data
        public static class  ModelTwo{
                private String attr1;
                private String attr2;
        }
}
