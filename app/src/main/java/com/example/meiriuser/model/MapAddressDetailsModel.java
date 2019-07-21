package com.example.meiriuser.model;

import java.util.List;

/**
 * Created by admin on 2019/7/10.
 */

public class MapAddressDetailsModel {

    /**
     * html_attributions : []
     * result : {"address_components":[{"long_name":"Sunnyvale","short_name":"Sunnyvale","types":["locality","political"]},{"long_name":"Santa Clara County","short_name":"Santa Clara County","types":["administrative_area_level_2","political"]},{"long_name":"California","short_name":"CA","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]}],"adr_address":"<span class=\"locality\">Sunnyvale<\/span>, <span class=\"region\">CA<\/span>, <span class=\"country-name\">USA<\/span>","formatted_address":"Sunnyvale, CA, USA","geometry":{"location":{"lat":37.36883,"lng":-122.0363496},"viewport":{"northeast":{"lat":37.4612324,"lng":-121.98267},"southwest":{"lat":37.330236,"lng":-122.0655719}}},"icon":"https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png","id":"25eeb97f0613345cf03cc355c8661335efe47c76","name":"Sunnyvale","photos":[{"height":2560,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116474959643488797662/photos\">cristobal garcia<\/a>"],"photo_reference":"CmRaAAAAC612L32x8gawkwm3Cy0jgitcaFa2dnechmkV6DSZbuPNYY9RMA4gxXNBHVHJ6KzKz91wO0rgzE0E9StRAFUV3ov9e3xr4VRnUBvaQahCt55kxvheVkbcm0sxyvGeZUYDEhD057a_1zNojkmDBLSektX8GhRPoaa1wKUCYS4B6q4brkdY7hJuWw","width":1920},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/110758740635783445304/photos\">Chen-Chih Lin<\/a>"],"photo_reference":"CmRaAAAAQ1u7dpze0-FfediwL5Dm3kI4iNu3Z3ruZYIscfbqzZIuPP_lqMHaHftSiOmF8zP4w98aBa4xXz-SXFjSb7TEN4h6a2SHtx9vRjb5Uq8Lhb1AXfhbgGXB0amrcnUK1dI6EhCndBAUts4R6pI-u4w1C7c2GhSI_ag2fRAQAIu9uYgPyB_XAYkmWg","width":5312},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/108561919824467197548/photos\">Joel Alapit<\/a>"],"photo_reference":"CmRZAAAAN0EOolJ85Mxnb44Jeq6YRHpXRTGcDz_IjJePjpf2nd5UlbyLmwZnE4Xa_9Y9LjzXOtG5iMla853KpBNbFJSsCTgrqbbb-wTKt5_kOQF4E1lgiQ8bJtacxMZHlLoVj2qBEhCT5wHv-OsSBgjB4YxmzLb2GhT013zJTTa1LR7WoA-m_RdWY--J3w","width":4032},{"height":1200,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/103410454781758949499/photos\">big daddy super strut solrac<\/a>"],"photo_reference":"CmRaAAAAlfNrCzkmvnhn1EACqvPAz1xTiJZ9aU321-DQDOGPwznV-rdagOo7U-NuCU9Yve3E_IDDDGbepFKfQppF-zgPoh_pcPq_FUafAk6W-3N5UMx_5GsnTeS-QH1yattU06nNEhC7WGycOFGjZhho5LHibUFrGhRJDA7EmyO9krKcQvGQGFZ_qIOFZw","width":1600},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/105577887918130373392/photos\">Lionel Jeon<\/a>"],"photo_reference":"CmRaAAAAw_D_YymISQBaS7i8oD7PAJZs_uATK55IEKnC7Vd1W0Smacs7hOF2BcHvN-xVLnLHNp9K_R3Ybnvjm7dZOdMjIxTiVBe11StCJLVObw6hTfH1-dOxRi5K7uT0501FgmNDEhB2VvMcgXBQfwGPCMmCbFosGhTdrd6fr1qV7ICAT5qC2PjxdAE2AQ","width":4032},{"height":2218,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/106648555272514207623/photos\">Aristo Wan<\/a>"],"photo_reference":"CmRaAAAAyhMF7iGAgHET869udA2QGx1tiEkxHCeWgHhudfRkNouePwdIRti7cHzYqR9h0pgGyDfeb9Gf6kJrxpCaLoDYjikcfnBXldRlr11NjKetQJpduLwSK4cRw7llz5WyGneQEhBFpl5UXgj7w4_Kacxw8yooGhR49HLfYaz-6VhdBu9qFmJKoeONig","width":4656},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/107146622280864463778/photos\">Leng Ooi<\/a>"],"photo_reference":"CmRaAAAA4-VHV38K_oGQvWU8rvli8vwXCbSCmIG3HB5DAqNAlOHLgu5WgRYfr6bSYLsFqbfXpcF8OmIaaspsoaxnzLr_1shSb-SPHalknyd35APO0zNTXL_EkZRehN_omLWn73jNEhBv8I6Ma0JJctWNRY0fbYr_GhS33H4A2e0vrOgBFWm9_kpezQyqGg","width":4032},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116240875349928873819/photos\">Lori Jones<\/a>"],"photo_reference":"CmRaAAAAqnmKLTuLwBCk2ePxQCHAQwdgEZan5FBLt6bhWXJ-c9fB98EFnyKB0gVbzYlIPWRFslEce_dgfl4AFvQ8RDnYWTU_gbFYK-Y-YDkh9bgW3QtI9s-Ii9JdDKyZCazmxQBGEhC_NeLi4UlkQav9e0BH5b2yGhTrEwKhTDpeSBb76Xxk2Yv_ltbm7A","width":5312},{"height":4032,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/102353370283648019889/photos\">Anastasiia Sviridova<\/a>"],"photo_reference":"CmRaAAAA2sPiUY287krdxGdxdb7_GMH_mC5ZKaJhR57IMEi1PdHijX6jfJzUF1ansnQatlQBAYwIXk3_xisqagDrt2Mbc3Q28SVwLSJXWXOkbfTTa6BU6pp7NdHEElYiTuCimeo4EhAcVqoWgANSIo4g3vJh5C0vGhSfsEhykyo2WKHW8TlkO1Kl6wwZsA","width":3024},{"height":2499,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/103864171625913401988/photos\">Jan Tkáč<\/a>"],"photo_reference":"CmRaAAAAonOoqo6RtHtOlKrcYc5w5KeOWlhakxo6vBVRCtTfu8h0s0XlctpooTuxUD0gymVcCPcXp9fj-qm0N38to6rG5qhXBCXD6gL_ZAGfuwCp86Lrp_EqN-WdDT2DPeMN1URlEhBaMJAuaNpqaaPluS4qp18mGhRmRL7QtOHsPdb12PISgaVHhBf8FQ","width":4160}],"place_id":"ChIJO13QqUW2j4ARosN83Sb7jXY","reference":"ChIJO13QqUW2j4ARosN83Sb7jXY","scope":"GOOGLE","types":["locality","political"],"url":"https://maps.google.com/?q=Sunnyvale,+CA,+USA&ftid=0x808fb645a9d05d3b:0x768dfb26dd7cc3a2","utc_offset":-420,"vicinity":"Sunnyvale","website":"http://www.sunnyvale.ca.gov/"}
     * status : OK
     */

    private ResultBean result;
    private String status;
    private List<?> html_attributions;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<?> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public static class ResultBean {
        /**
         * address_components : [{"long_name":"Sunnyvale","short_name":"Sunnyvale","types":["locality","political"]},{"long_name":"Santa Clara County","short_name":"Santa Clara County","types":["administrative_area_level_2","political"]},{"long_name":"California","short_name":"CA","types":["administrative_area_level_1","political"]},{"long_name":"United States","short_name":"US","types":["country","political"]}]
         * adr_address : <span class="locality">Sunnyvale</span>, <span class="region">CA</span>, <span class="country-name">USA</span>
         * formatted_address : Sunnyvale, CA, USA
         * geometry : {"location":{"lat":37.36883,"lng":-122.0363496},"viewport":{"northeast":{"lat":37.4612324,"lng":-121.98267},"southwest":{"lat":37.330236,"lng":-122.0655719}}}
         * icon : https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png
         * id : 25eeb97f0613345cf03cc355c8661335efe47c76
         * name : Sunnyvale
         * photos : [{"height":2560,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116474959643488797662/photos\">cristobal garcia<\/a>"],"photo_reference":"CmRaAAAAC612L32x8gawkwm3Cy0jgitcaFa2dnechmkV6DSZbuPNYY9RMA4gxXNBHVHJ6KzKz91wO0rgzE0E9StRAFUV3ov9e3xr4VRnUBvaQahCt55kxvheVkbcm0sxyvGeZUYDEhD057a_1zNojkmDBLSektX8GhRPoaa1wKUCYS4B6q4brkdY7hJuWw","width":1920},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/110758740635783445304/photos\">Chen-Chih Lin<\/a>"],"photo_reference":"CmRaAAAAQ1u7dpze0-FfediwL5Dm3kI4iNu3Z3ruZYIscfbqzZIuPP_lqMHaHftSiOmF8zP4w98aBa4xXz-SXFjSb7TEN4h6a2SHtx9vRjb5Uq8Lhb1AXfhbgGXB0amrcnUK1dI6EhCndBAUts4R6pI-u4w1C7c2GhSI_ag2fRAQAIu9uYgPyB_XAYkmWg","width":5312},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/108561919824467197548/photos\">Joel Alapit<\/a>"],"photo_reference":"CmRZAAAAN0EOolJ85Mxnb44Jeq6YRHpXRTGcDz_IjJePjpf2nd5UlbyLmwZnE4Xa_9Y9LjzXOtG5iMla853KpBNbFJSsCTgrqbbb-wTKt5_kOQF4E1lgiQ8bJtacxMZHlLoVj2qBEhCT5wHv-OsSBgjB4YxmzLb2GhT013zJTTa1LR7WoA-m_RdWY--J3w","width":4032},{"height":1200,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/103410454781758949499/photos\">big daddy super strut solrac<\/a>"],"photo_reference":"CmRaAAAAlfNrCzkmvnhn1EACqvPAz1xTiJZ9aU321-DQDOGPwznV-rdagOo7U-NuCU9Yve3E_IDDDGbepFKfQppF-zgPoh_pcPq_FUafAk6W-3N5UMx_5GsnTeS-QH1yattU06nNEhC7WGycOFGjZhho5LHibUFrGhRJDA7EmyO9krKcQvGQGFZ_qIOFZw","width":1600},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/105577887918130373392/photos\">Lionel Jeon<\/a>"],"photo_reference":"CmRaAAAAw_D_YymISQBaS7i8oD7PAJZs_uATK55IEKnC7Vd1W0Smacs7hOF2BcHvN-xVLnLHNp9K_R3Ybnvjm7dZOdMjIxTiVBe11StCJLVObw6hTfH1-dOxRi5K7uT0501FgmNDEhB2VvMcgXBQfwGPCMmCbFosGhTdrd6fr1qV7ICAT5qC2PjxdAE2AQ","width":4032},{"height":2218,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/106648555272514207623/photos\">Aristo Wan<\/a>"],"photo_reference":"CmRaAAAAyhMF7iGAgHET869udA2QGx1tiEkxHCeWgHhudfRkNouePwdIRti7cHzYqR9h0pgGyDfeb9Gf6kJrxpCaLoDYjikcfnBXldRlr11NjKetQJpduLwSK4cRw7llz5WyGneQEhBFpl5UXgj7w4_Kacxw8yooGhR49HLfYaz-6VhdBu9qFmJKoeONig","width":4656},{"height":3024,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/107146622280864463778/photos\">Leng Ooi<\/a>"],"photo_reference":"CmRaAAAA4-VHV38K_oGQvWU8rvli8vwXCbSCmIG3HB5DAqNAlOHLgu5WgRYfr6bSYLsFqbfXpcF8OmIaaspsoaxnzLr_1shSb-SPHalknyd35APO0zNTXL_EkZRehN_omLWn73jNEhBv8I6Ma0JJctWNRY0fbYr_GhS33H4A2e0vrOgBFWm9_kpezQyqGg","width":4032},{"height":2988,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/116240875349928873819/photos\">Lori Jones<\/a>"],"photo_reference":"CmRaAAAAqnmKLTuLwBCk2ePxQCHAQwdgEZan5FBLt6bhWXJ-c9fB98EFnyKB0gVbzYlIPWRFslEce_dgfl4AFvQ8RDnYWTU_gbFYK-Y-YDkh9bgW3QtI9s-Ii9JdDKyZCazmxQBGEhC_NeLi4UlkQav9e0BH5b2yGhTrEwKhTDpeSBb76Xxk2Yv_ltbm7A","width":5312},{"height":4032,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/102353370283648019889/photos\">Anastasiia Sviridova<\/a>"],"photo_reference":"CmRaAAAA2sPiUY287krdxGdxdb7_GMH_mC5ZKaJhR57IMEi1PdHijX6jfJzUF1ansnQatlQBAYwIXk3_xisqagDrt2Mbc3Q28SVwLSJXWXOkbfTTa6BU6pp7NdHEElYiTuCimeo4EhAcVqoWgANSIo4g3vJh5C0vGhSfsEhykyo2WKHW8TlkO1Kl6wwZsA","width":3024},{"height":2499,"html_attributions":["<a href=\"https://maps.google.com/maps/contrib/103864171625913401988/photos\">Jan Tkáč<\/a>"],"photo_reference":"CmRaAAAAonOoqo6RtHtOlKrcYc5w5KeOWlhakxo6vBVRCtTfu8h0s0XlctpooTuxUD0gymVcCPcXp9fj-qm0N38to6rG5qhXBCXD6gL_ZAGfuwCp86Lrp_EqN-WdDT2DPeMN1URlEhBaMJAuaNpqaaPluS4qp18mGhRmRL7QtOHsPdb12PISgaVHhBf8FQ","width":4160}]
         * place_id : ChIJO13QqUW2j4ARosN83Sb7jXY
         * reference : ChIJO13QqUW2j4ARosN83Sb7jXY
         * scope : GOOGLE
         * types : ["locality","political"]
         * url : https://maps.google.com/?q=Sunnyvale,+CA,+USA&ftid=0x808fb645a9d05d3b:0x768dfb26dd7cc3a2
         * utc_offset : -420
         * vicinity : Sunnyvale
         * website : http://www.sunnyvale.ca.gov/
         */

        private String adr_address;
        private String formatted_address;
        private GeometryBean geometry;
        private String icon;
        private String id;
        private String name;
        private String place_id;
        private String reference;
        private String scope;
        private String url;
        private int utc_offset;
        private String vicinity;
        private String website;
        private List<AddressComponentsBean> address_components;
        private List<PhotosBean> photos;
        private List<String> types;

        public String getAdr_address() {
            return adr_address;
        }

        public void setAdr_address(String adr_address) {
            this.adr_address = adr_address;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryBean getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryBean geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUtc_offset() {
            return utc_offset;
        }

        public void setUtc_offset(int utc_offset) {
            this.utc_offset = utc_offset;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public List<AddressComponentsBean> getAddress_components() {
            return address_components;
        }

        public void setAddress_components(List<AddressComponentsBean> address_components) {
            this.address_components = address_components;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public static class GeometryBean {
            /**
             * location : {"lat":37.36883,"lng":-122.0363496}
             * viewport : {"northeast":{"lat":37.4612324,"lng":-121.98267},"southwest":{"lat":37.330236,"lng":-122.0655719}}
             */

            private LocationBean location;
            private ViewportBean viewport;

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public ViewportBean getViewport() {
                return viewport;
            }

            public void setViewport(ViewportBean viewport) {
                this.viewport = viewport;
            }

            public static class LocationBean {
                /**
                 * lat : 37.36883
                 * lng : -122.0363496
                 */

                private double lat;
                private double lng;

                public double getLat() {
                    return lat;
                }

                public void setLat(double lat) {
                    this.lat = lat;
                }

                public double getLng() {
                    return lng;
                }

                public void setLng(double lng) {
                    this.lng = lng;
                }
            }

            public static class ViewportBean {
                /**
                 * northeast : {"lat":37.4612324,"lng":-121.98267}
                 * southwest : {"lat":37.330236,"lng":-122.0655719}
                 */

                private NortheastBean northeast;
                private SouthwestBean southwest;

                public NortheastBean getNortheast() {
                    return northeast;
                }

                public void setNortheast(NortheastBean northeast) {
                    this.northeast = northeast;
                }

                public SouthwestBean getSouthwest() {
                    return southwest;
                }

                public void setSouthwest(SouthwestBean southwest) {
                    this.southwest = southwest;
                }

                public static class NortheastBean {
                    /**
                     * lat : 37.4612324
                     * lng : -121.98267
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }

                public static class SouthwestBean {
                    /**
                     * lat : 37.330236
                     * lng : -122.0655719
                     */

                    private double lat;
                    private double lng;

                    public double getLat() {
                        return lat;
                    }

                    public void setLat(double lat) {
                        this.lat = lat;
                    }

                    public double getLng() {
                        return lng;
                    }

                    public void setLng(double lng) {
                        this.lng = lng;
                    }
                }
            }
        }

        public static class AddressComponentsBean {
            /**
             * long_name : Sunnyvale
             * short_name : Sunnyvale
             * types : ["locality","political"]
             */

            private String long_name;
            private String short_name;
            private List<String> types;

            public String getLong_name() {
                return long_name;
            }

            public void setLong_name(String long_name) {
                this.long_name = long_name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public List<String> getTypes() {
                return types;
            }

            public void setTypes(List<String> types) {
                this.types = types;
            }
        }

        public static class PhotosBean {
            /**
             * height : 2560
             * html_attributions : ["<a href=\"https://maps.google.com/maps/contrib/116474959643488797662/photos\">cristobal garcia<\/a>"]
             * photo_reference : CmRaAAAAC612L32x8gawkwm3Cy0jgitcaFa2dnechmkV6DSZbuPNYY9RMA4gxXNBHVHJ6KzKz91wO0rgzE0E9StRAFUV3ov9e3xr4VRnUBvaQahCt55kxvheVkbcm0sxyvGeZUYDEhD057a_1zNojkmDBLSektX8GhRPoaa1wKUCYS4B6q4brkdY7hJuWw
             * width : 1920
             */

            private int height;
            private String photo_reference;
            private int width;
            private List<String> html_attributions;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public List<String> getHtml_attributions() {
                return html_attributions;
            }

            public void setHtml_attributions(List<String> html_attributions) {
                this.html_attributions = html_attributions;
            }
        }
    }
}
