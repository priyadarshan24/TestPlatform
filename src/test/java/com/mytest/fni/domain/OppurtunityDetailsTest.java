package com.mytest.fni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mytest.fni.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OppurtunityDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OppurtunityDetails.class);
        OppurtunityDetails oppurtunityDetails1 = new OppurtunityDetails();
        oppurtunityDetails1.setId(1L);
        OppurtunityDetails oppurtunityDetails2 = new OppurtunityDetails();
        oppurtunityDetails2.setId(oppurtunityDetails1.getId());
        assertThat(oppurtunityDetails1).isEqualTo(oppurtunityDetails2);
        oppurtunityDetails2.setId(2L);
        assertThat(oppurtunityDetails1).isNotEqualTo(oppurtunityDetails2);
        oppurtunityDetails1.setId(null);
        assertThat(oppurtunityDetails1).isNotEqualTo(oppurtunityDetails2);
    }
}
