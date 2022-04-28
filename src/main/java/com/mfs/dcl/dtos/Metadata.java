package com.mfs.dcl.dtos;

import lombok.Getter;
import lombok.Setter;

/*
 *  Metadata Class
 *  @since 28-04-2022
 */

@Setter
@Getter
public class Metadata {
    public String sender_name;
    public String sender_country;
    public String sender_id_type;
    public String sender_id_number;
    public String recipient_country;
    public String recipient_id_type;
    public String recipient_id_number;

    //toString Method
    @Override
    public String toString() {
        return "Metadata [sender_name=" + sender_name + ", sender_country=" + sender_country + ", sender_id_type=" + sender_id_type
                + ", sender_id_number=" + sender_id_number + ", recipient_country=" + recipient_country + ", recipient_id_type="
                + recipient_id_type + ", recipient_id_number=" + recipient_id_number + "]";
    }
}