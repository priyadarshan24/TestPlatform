import dayjs from 'dayjs/esm';
import { IFniTOFinancierSyncStatus } from 'app/entities/fni-to-financier-sync-status/fni-to-financier-sync-status.model';

export interface IOppurtunityDetails {
  id?: number;
  crmOppurtunityID?: string | null;
  oppurtunityCreatedDate?: dayjs.Dayjs | null;
  crmCustomerID?: number | null;
  bdmName?: string | null;
  bdmID?: string | null;
  dseID?: string | null;
  dseName?: string | null;
  accountType?: string | null;
  accountName?: string | null;
  accountSite?: string | null;
  vehicleClass?: string | null;
  vehicleVariant?: string | null;
  engineCapacity?: string | null;
  fuelTankCapacity?: string | null;
  wheelBase?: string | null;
  power?: string | null;
  gvwWeight?: string | null;
  payloadWeight?: string | null;
  exShowRoomPrice?: number | null;
  onRoadPrice?: number | null;
  lob?: string | null;
  ppl?: string | null;
  pl?: string | null;
  financierSyncStatus?: IFniTOFinancierSyncStatus | null;
}

export class OppurtunityDetails implements IOppurtunityDetails {
  constructor(
    public id?: number,
    public crmOppurtunityID?: string | null,
    public oppurtunityCreatedDate?: dayjs.Dayjs | null,
    public crmCustomerID?: number | null,
    public bdmName?: string | null,
    public bdmID?: string | null,
    public dseID?: string | null,
    public dseName?: string | null,
    public accountType?: string | null,
    public accountName?: string | null,
    public accountSite?: string | null,
    public vehicleClass?: string | null,
    public vehicleVariant?: string | null,
    public engineCapacity?: string | null,
    public fuelTankCapacity?: string | null,
    public wheelBase?: string | null,
    public power?: string | null,
    public gvwWeight?: string | null,
    public payloadWeight?: string | null,
    public exShowRoomPrice?: number | null,
    public onRoadPrice?: number | null,
    public lob?: string | null,
    public ppl?: string | null,
    public pl?: string | null,
    public financierSyncStatus?: IFniTOFinancierSyncStatus | null
  ) {}
}

export function getOppurtunityDetailsIdentifier(oppurtunityDetails: IOppurtunityDetails): number | undefined {
  return oppurtunityDetails.id;
}
