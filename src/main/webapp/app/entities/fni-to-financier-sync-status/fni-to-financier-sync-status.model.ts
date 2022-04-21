import dayjs from 'dayjs/esm';
import { SyncStatus } from 'app/entities/enumerations/sync-status.model';

export interface IFniTOFinancierSyncStatus {
  id?: number;
  syncDateTimeStamp?: dayjs.Dayjs | null;
  syncStatus?: SyncStatus | null;
  comments?: string | null;
}

export class FniTOFinancierSyncStatus implements IFniTOFinancierSyncStatus {
  constructor(
    public id?: number,
    public syncDateTimeStamp?: dayjs.Dayjs | null,
    public syncStatus?: SyncStatus | null,
    public comments?: string | null
  ) {}
}

export function getFniTOFinancierSyncStatusIdentifier(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): number | undefined {
  return fniTOFinancierSyncStatus.id;
}
