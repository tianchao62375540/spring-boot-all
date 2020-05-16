package com.tc.security.springboot.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/4/4 15:23
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebSecurityConfigTest {

    @Test
    public void testBCrypt(){
        String gensalt = BCrypt.gensalt();
        System.out.println(gensalt);
        String hashpw = BCrypt.hashpw("secret", gensalt);
        System.out.println(hashpw);
        //$2a$10$NhDbmSTtF/kei7/Zq7AyJOH7/m9BahojbwJa9KDtjL3cFSMM5S2BC
        //$2a$10$kGrrqDBsxEv4avACOjJ8teBjaG3kRsRiySskVaVKJzS8Mz2X2WKlO
        /**
         * $2a$10$ETe7n0x1.UUInrfIDNQDHO
         * $2a$10$ETe7n0x1.UUInrfIDNQDHO7yXpB8WyjL7RnjNBMD4vhG6ev8HGF9m
         */
        /**
         * $2a$10$MT5gbBvReZm.6s84r44HwO
         * $2a$10$MT5gbBvReZm.6s84r44HwO.09Hkshgv4LIVpH7KTd3Kf6gmEZQPcG
         */
        System.out.println(BCrypt.checkpw("123", "$2a$10$NhDbmSTtF/kei7/Zq7AyJOH7/m9BahojbwJa9KDtjL3cFSMM5S2BC"));
        System.out.println(BCrypt.checkpw("123", "$2a$10$kGrrqDBsxEv4avACOjJ8teBjaG3kRsRiySskVaVKJzS8Mz2X2WKlO"));
        System.out.println(BCrypt.checkpw("123", "$2a$10$ETe7n0x1.UUInrfIDNQDHO7yXpB8WyjL7RnjNBMD4vhG6ev8HGF9m"));
        System.out.println(BCrypt.checkpw("123", "$2a$10$MT5gbBvReZm.6s84r44HwO.09Hkshgv4LIVpH7KTd3Kf6gmEZQPcG"));
    }
}