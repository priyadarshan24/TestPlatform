import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { SyncStatus } from 'app/entities/enumerations/sync-status.model';
import { IFniTOFinancierSyncStatus, FniTOFinancierSyncStatus } from '../fni-to-financier-sync-status.model';

import { FniTOFinancierSyncStatusService } from './fni-to-financier-sync-status.service';

describe('FniTOFinancierSyncStatus Service', () => {
  let service: FniTOFinancierSyncStatusService;
  let httpMock: HttpTestingController;
  let elemDefault: IFniTOFinancierSyncStatus;
  let expectedResult: IFniTOFinancierSyncStatus | IFniTOFinancierSyncStatus[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FniTOFinancierSyncStatusService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      syncDateTimeStamp: currentDate,
      syncStatus: SyncStatus.PENDING,
      comments: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          syncDateTimeStamp: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a FniTOFinancierSyncStatus', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          syncDateTimeStamp: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          syncDateTimeStamp: currentDate,
        },
        returnedFromService
      );

      service.create(new FniTOFinancierSyncStatus()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FniTOFinancierSyncStatus', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          syncDateTimeStamp: currentDate.format(DATE_FORMAT),
          syncStatus: 'BBBBBB',
          comments: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          syncDateTimeStamp: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FniTOFinancierSyncStatus', () => {
      const patchObject = Object.assign(
        {
          syncDateTimeStamp: currentDate.format(DATE_FORMAT),
          syncStatus: 'BBBBBB',
          comments: 'BBBBBB',
        },
        new FniTOFinancierSyncStatus()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          syncDateTimeStamp: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FniTOFinancierSyncStatus', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          syncDateTimeStamp: currentDate.format(DATE_FORMAT),
          syncStatus: 'BBBBBB',
          comments: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          syncDateTimeStamp: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a FniTOFinancierSyncStatus', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addFniTOFinancierSyncStatusToCollectionIfMissing', () => {
      it('should add a FniTOFinancierSyncStatus to an empty array', () => {
        const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 123 };
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing([], fniTOFinancierSyncStatus);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fniTOFinancierSyncStatus);
      });

      it('should not add a FniTOFinancierSyncStatus to an array that contains it', () => {
        const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 123 };
        const fniTOFinancierSyncStatusCollection: IFniTOFinancierSyncStatus[] = [
          {
            ...fniTOFinancierSyncStatus,
          },
          { id: 456 },
        ];
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing(
          fniTOFinancierSyncStatusCollection,
          fniTOFinancierSyncStatus
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FniTOFinancierSyncStatus to an array that doesn't contain it", () => {
        const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 123 };
        const fniTOFinancierSyncStatusCollection: IFniTOFinancierSyncStatus[] = [{ id: 456 }];
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing(
          fniTOFinancierSyncStatusCollection,
          fniTOFinancierSyncStatus
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fniTOFinancierSyncStatus);
      });

      it('should add only unique FniTOFinancierSyncStatus to an array', () => {
        const fniTOFinancierSyncStatusArray: IFniTOFinancierSyncStatus[] = [{ id: 123 }, { id: 456 }, { id: 89151 }];
        const fniTOFinancierSyncStatusCollection: IFniTOFinancierSyncStatus[] = [{ id: 123 }];
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing(
          fniTOFinancierSyncStatusCollection,
          ...fniTOFinancierSyncStatusArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 123 };
        const fniTOFinancierSyncStatus2: IFniTOFinancierSyncStatus = { id: 456 };
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing([], fniTOFinancierSyncStatus, fniTOFinancierSyncStatus2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(fniTOFinancierSyncStatus);
        expect(expectedResult).toContain(fniTOFinancierSyncStatus2);
      });

      it('should accept null and undefined values', () => {
        const fniTOFinancierSyncStatus: IFniTOFinancierSyncStatus = { id: 123 };
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing([], null, fniTOFinancierSyncStatus, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(fniTOFinancierSyncStatus);
      });

      it('should return initial array if no FniTOFinancierSyncStatus is added', () => {
        const fniTOFinancierSyncStatusCollection: IFniTOFinancierSyncStatus[] = [{ id: 123 }];
        expectedResult = service.addFniTOFinancierSyncStatusToCollectionIfMissing(fniTOFinancierSyncStatusCollection, undefined, null);
        expect(expectedResult).toEqual(fniTOFinancierSyncStatusCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
