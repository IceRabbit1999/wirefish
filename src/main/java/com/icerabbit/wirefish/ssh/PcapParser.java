package com.icerabbit.wirefish.ssh;

import com.icerabbit.wirefish.ssh.kaitai.Pcap;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author iceRabbit
 * @Date 7/21/22 4:08 PM
 **/
public class PcapParser {
    public static void main(String[] args) throws IOException {
        Pcap data = Pcap.fromFile("/work/project/paas_oam/paas_oam_back/src/main/java/com/cmic/paas_oam_back/service/ssh/t.pcap");

        Pcap.Header hdr = data.hdr();

        System.out.println(data.hdr().network().name());


        ArrayList<Pcap.Packet> packets = data.packets();
        for (Pcap.Packet packet : packets){
            byte[] body = (byte[]) packet.body();
            System.out.println(body[25]);
        }
    }
}
