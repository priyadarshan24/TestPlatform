package com.mytest.fni.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mytest.fni.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FniTOFinancierSyncStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FniTOFinancierSyncStatus.class);
        FniTOFinancierSyncStatus fniTOFinancierSyncStatus1 = new FniTOFinancierSyncStatus();
        fniTOFinancierSyncStatus1.setId(1L);
        FniTOFinancierSyncStatus fniTOFinancierSyncStatus2 = new FniTOFinancierSyncStatus();
        fniTOFinancierSyncStatus2.setId(fniTOFinancierSyncStatus1.getId());
        assertThat(fniTOFinancierSyncStatus1).isEqualTo(fniTOFinancierSyncStatus2);
        fniTOFinancierSyncStatus2.setId(2L);
        assertThat(fniTOFinancierSyncStatus1).isNotEqualTo(fniTOFinancierSyncStatus2);
        fniTOFinancierSyncStatus1.setId(null);
        assertThat(fniTOFinancierSyncStatus1).isNotEqualTo(fniTOFinancierSyncStatus2);
    }
}
