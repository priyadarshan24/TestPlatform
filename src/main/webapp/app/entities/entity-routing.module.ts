import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'oppurtunity-details',
        data: { pageTitle: 'OppurtunityDetails' },
        loadChildren: () => import('./oppurtunity-details/oppurtunity-details.module').then(m => m.OppurtunityDetailsModule),
      },
      {
        path: 'fni-to-financier-sync-status',
        data: { pageTitle: 'FniTOFinancierSyncStatuses' },
        loadChildren: () =>
          import('./fni-to-financier-sync-status/fni-to-financier-sync-status.module').then(m => m.FniTOFinancierSyncStatusModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
