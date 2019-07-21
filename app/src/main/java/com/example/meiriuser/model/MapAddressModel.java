package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/7/9.
 */

public class MapAddressModel {

    /**
     * predictions : [{"description":"Sunnyvale, CA, USA","id":"25eeb97f0613345cf03cc355c8661335efe47c76","matched_substrings":[{"length":3,"offset":0}],"place_id":"ChIJO13QqUW2j4ARosN83Sb7jXY","reference":"ChIJO13QqUW2j4ARosN83Sb7jXY","structured_formatting":{"main_text":"Sunnyvale","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"CA, USA"},"terms":[{"offset":0,"value":"Sunnyvale"},{"offset":11,"value":"CA"},{"offset":15,"value":"USA"}],"types":["locality","political","geocode"]},{"description":"Sunderland, UK","id":"18b53d7f2a1d7b4f57d0605b57df389593b9a470","matched_substrings":[{"length":3,"offset":0}],"place_id":"ChIJn3Pwii1hfkgRwwN_AX1unXQ","reference":"ChIJn3Pwii1hfkgRwwN_AX1unXQ","structured_formatting":{"main_text":"Sunderland","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"UK"},"terms":[{"offset":0,"value":"Sunderland"},{"offset":12,"value":"UK"}],"types":["locality","political","geocode"]},{"description":"Sundsvall, Sweden","id":"ffc53d9b97853b7677118b981d70fc7061166032","matched_substrings":[{"length":3,"offset":0}],"place_id":"ChIJH3bGnUxnZEYRIGEWn6ybP2g","reference":"ChIJH3bGnUxnZEYRIGEWn6ybP2g","structured_formatting":{"main_text":"Sundsvall","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"Sweden"},"terms":[{"offset":0,"value":"Sundsvall"},{"offset":11,"value":"Sweden"}],"types":["locality","political","geocode"]},{"description":"Sungai Petani, Kedah, Malaysia","id":"373e51bd5736222e64d1b6f528b0b9f9a9c1af1d","matched_substrings":[{"length":3,"offset":0}],"place_id":"ChIJz4sPk80pSzARA3lZOYpwtcs","reference":"ChIJz4sPk80pSzARA3lZOYpwtcs","structured_formatting":{"main_text":"Sungai Petani","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"Kedah, Malaysia"},"terms":[{"offset":0,"value":"Sungai Petani"},{"offset":15,"value":"Kedah"},{"offset":22,"value":"Malaysia"}],"types":["locality","political","geocode"]},{"description":"Sunshine Coast QLD, Australia","id":"deec0512e552f17da1d185c2d41b21ab6afd94ae","matched_substrings":[{"length":3,"offset":0}],"place_id":"ChIJ7Uh9bbZ_k2sRUK_e81qjAgM","reference":"ChIJ7Uh9bbZ_k2sRUK_e81qjAgM","structured_formatting":{"main_text":"Sunshine Coast","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"QLD, Australia"},"terms":[{"offset":0,"value":"Sunshine Coast"},{"offset":15,"value":"QLD"},{"offset":20,"value":"Australia"}],"types":["colloquial_area","locality","political","geocode"]}]
     * status : OK
     */

    private String status;
    private List<PredictionsBean> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PredictionsBean> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<PredictionsBean> predictions) {
        this.predictions = predictions;
    }

    public static class PredictionsBean {
        /**
         * description : Sunnyvale, CA, USA
         * id : 25eeb97f0613345cf03cc355c8661335efe47c76
         * matched_substrings : [{"length":3,"offset":0}]
         * place_id : ChIJO13QqUW2j4ARosN83Sb7jXY
         * reference : ChIJO13QqUW2j4ARosN83Sb7jXY
         * structured_formatting : {"main_text":"Sunnyvale","main_text_matched_substrings":[{"length":3,"offset":0}],"secondary_text":"CA, USA"}
         * terms : [{"offset":0,"value":"Sunnyvale"},{"offset":11,"value":"CA"},{"offset":15,"value":"USA"}]
         * types : ["locality","political","geocode"]
         */

        private String description;
        private String id;
        private String place_id;
        private String reference;
        private StructuredFormattingBean structured_formatting;
        private List<MatchedSubstringsBean> matched_substrings;
        private List<TermsBean> terms;
        private List<String> types;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public StructuredFormattingBean getStructured_formatting() {
            return structured_formatting;
        }

        public void setStructured_formatting(StructuredFormattingBean structured_formatting) {
            this.structured_formatting = structured_formatting;
        }

        public List<MatchedSubstringsBean> getMatched_substrings() {
            return matched_substrings;
        }

        public void setMatched_substrings(List<MatchedSubstringsBean> matched_substrings) {
            this.matched_substrings = matched_substrings;
        }

        public List<TermsBean> getTerms() {
            return terms;
        }

        public void setTerms(List<TermsBean> terms) {
            this.terms = terms;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class StructuredFormattingBean {
            /**
             * main_text : Sunnyvale
             * main_text_matched_substrings : [{"length":3,"offset":0}]
             * secondary_text : CA, USA
             */

            private String main_text;
            private String secondary_text;
            private List<MainTextMatchedSubstringsBean> main_text_matched_substrings;

            public String getMain_text() {
                return main_text;
            }

            public void setMain_text(String main_text) {
                this.main_text = main_text;
            }

            public String getSecondary_text() {
                return secondary_text;
            }

            public void setSecondary_text(String secondary_text) {
                this.secondary_text = secondary_text;
            }

            public List<MainTextMatchedSubstringsBean> getMain_text_matched_substrings() {
                return main_text_matched_substrings;
            }

            public void setMain_text_matched_substrings(List<MainTextMatchedSubstringsBean> main_text_matched_substrings) {
                this.main_text_matched_substrings = main_text_matched_substrings;
            }

            public static class MainTextMatchedSubstringsBean {
                /**
                 * length : 3
                 * offset : 0
                 */

                private int length;
                private int offset;

                public int getLength() {
                    return length;
                }

                public void setLength(int length) {
                    this.length = length;
                }

                public int getOffset() {
                    return offset;
                }

                public void setOffset(int offset) {
                    this.offset = offset;
                }
            }
        }

        public static class MatchedSubstringsBean {
            /**
             * length : 3
             * offset : 0
             */

            private int length;
            private int offset;

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class TermsBean {
            /**
             * offset : 0
             * value : Sunnyvale
             */

            private int offset;
            private String value;

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
