import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFniTOFinancierSyncStatus, getFniTOFinancierSyncStatusIdentifier } from '../fni-to-financier-sync-status.model';

export type EntityResponseType = HttpResponse<IFniTOFinancierSyncStatus>;
export type EntityArrayResponseType = HttpResponse<IFniTOFinancierSyncStatus[]>;

@Injectable({ providedIn: 'root' })
export class FniTOFinancierSyncStatusService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fni-to-financier-sync-statuses');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fniTOFinancierSyncStatus);
    return this.http
      .post<IFniTOFinancierSyncStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fniTOFinancierSyncStatus);
    return this.http
      .put<IFniTOFinancierSyncStatus>(
        `${this.resourceUrl}/${getFniTOFinancierSyncStatusIdentifier(fniTOFinancierSyncStatus) as number}`,
        copy,
        { observe: 'response' }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fniTOFinancierSyncStatus);
    return this.http
      .patch<IFniTOFinancierSyncStatus>(
        `${this.resourceUrl}/${getFniTOFinancierSyncStatusIdentifier(fniTOFinancierSyncStatus) as number}`,
        copy,
        { observe: 'response' }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFniTOFinancierSyncStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFniTOFinancierSyncStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addFniTOFinancierSyncStatusToCollectionIfMissing(
    fniTOFinancierSyncStatusCollection: IFniTOFinancierSyncStatus[],
    ...fniTOFinancierSyncStatusesToCheck: (IFniTOFinancierSyncStatus | null | undefined)[]
  ): IFniTOFinancierSyncStatus[] {
    const fniTOFinancierSyncStatuses: IFniTOFinancierSyncStatus[] = fniTOFinancierSyncStatusesToCheck.filter(isPresent);
    if (fniTOFinancierSyncStatuses.length > 0) {
      const fniTOFinancierSyncStatusCollectionIdentifiers = fniTOFinancierSyncStatusCollection.map(
        fniTOFinancierSyncStatusItem => getFniTOFinancierSyncStatusIdentifier(fniTOFinancierSyncStatusItem)!
      );
      const fniTOFinancierSyncStatusesToAdd = fniTOFinancierSyncStatuses.filter(fniTOFinancierSyncStatusItem => {
        const fniTOFinancierSyncStatusIdentifier = getFniTOFinancierSyncStatusIdentifier(fniTOFinancierSyncStatusItem);
        if (
          fniTOFinancierSyncStatusIdentifier == null ||
          fniTOFinancierSyncStatusCollectionIdentifiers.includes(fniTOFinancierSyncStatusIdentifier)
        ) {
          return false;
        }
        fniTOFinancierSyncStatusCollectionIdentifiers.push(fniTOFinancierSyncStatusIdentifier);
        return true;
      });
      return [...fniTOFinancierSyncStatusesToAdd, ...fniTOFinancierSyncStatusCollection];
    }
    return fniTOFinancierSyncStatusCollection;
  }

  protected convertDateFromClient(fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus): IFniTOFinancierSyncStatus {
    return Object.assign({}, fniTOFinancierSyncStatus, {
      syncDateTimeStamp: fniTOFinancierSyncStatus.syncDateTimeStamp?.isValid()
        ? fniTOFinancierSyncStatus.syncDateTimeStamp.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.syncDateTimeStamp = res.body.syncDateTimeStamp ? dayjs(res.body.syncDateTimeStamp) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus) => {
        fniTOFinancierSyncStatus.syncDateTimeStamp = fniTOFinancierSyncStatus.syncDateTimeStamp
          ? dayjs(fniTOFinancierSyncStatus.syncDateTimeStamp)
          : undefined;
      });
    }
    return res;
  }
}
