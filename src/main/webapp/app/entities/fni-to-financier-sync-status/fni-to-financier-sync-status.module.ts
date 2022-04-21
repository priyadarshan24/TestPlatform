import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FniTOFinancierSyncStatusComponent } from './list/fni-to-financier-sync-status.component';
import { FniTOFinancierSyncStatusDetailComponent } from './detail/fni-to-financier-sync-status-detail.component';
import { FniTOFinancierSyncStatusUpdateComponent } from './update/fni-to-financier-sync-status-update.component';
import { FniTOFinancierSyncStatusDeleteDialogComponent } from './delete/fni-to-financier-sync-status-delete-dialog.component';
import { FniTOFinancierSyncStatusRoutingModule } from './route/fni-to-financier-sync-status-routing.module';

@NgModule({
  imports: [SharedModule, FniTOFinancierSyncStatusRoutingModule],
  declarations: [
    FniTOFinancierSyncStatusComponent,
    FniTOFinancierSyncStatusDetailComponent,
    FniTOFinancierSyncStatusUpdateComponent,
    FniTOFinancierSyncStatusDeleteDialogComponent,
  ],
  entryComponents: [FniTOFinancierSyncStatusDeleteDialogComponent],
})
export class FniTOFinancierSyncStatusModule {}
