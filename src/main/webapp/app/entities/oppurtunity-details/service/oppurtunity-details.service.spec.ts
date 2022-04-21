import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IOppurtunityDetails, OppurtunityDetails } from '../oppurtunity-details.model';

import { OppurtunityDetailsService } from './oppurtunity-details.service';

describe('OppurtunityDetails Service', () => {
  let service: OppurtunityDetailsService;
  let httpMock: HttpTestingController;
  let elemDefault: IOppurtunityDetails;
  let expectedResult: IOppurtunityDetails | IOppurtunityDetails[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(OppurtunityDetailsService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      crmOppurtunityID: 'AAAAAAA',
      oppurtunityCreatedDate: currentDate,
      crmCustomerID: 0,
      bdmName: 'AAAAAAA',
      bdmID: 'AAAAAAA',
      dseID: 'AAAAAAA',
      dseName: 'AAAAAAA',
      accountType: 'AAAAAAA',
      accountName: 'AAAAAAA',
      accountSite: 'AAAAAAA',
      vehicleClass: 'AAAAAAA',
      vehicleVariant: 'AAAAAAA',
      engineCapacity: 'AAAAAAA',
      fuelTankCapacity: 'AAAAAAA',
      wheelBase: 'AAAAAAA',
      power: 'AAAAAAA',
      gvwWeight: 'AAAAAAA',
      payloadWeight: 'AAAAAAA',
      exShowRoomPrice: 0,
      onRoadPrice: 0,
      lob: 'AAAAAAA',
      ppl: 'AAAAAAA',
      pl: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          oppurtunityCreatedDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a OppurtunityDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          oppurtunityCreatedDate: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          oppurtunityCreatedDate: currentDate,
        },
        returnedFromService
      );

      service.create(new OppurtunityDetails()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a OppurtunityDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          crmOppurtunityID: 'BBBBBB',
          oppurtunityCreatedDate: currentDate.format(DATE_FORMAT),
          crmCustomerID: 1,
          bdmName: 'BBBBBB',
          bdmID: 'BBBBBB',
          dseID: 'BBBBBB',
          dseName: 'BBBBBB',
          accountType: 'BBBBBB',
          accountName: 'BBBBBB',
          accountSite: 'BBBBBB',
          vehicleClass: 'BBBBBB',
          vehicleVariant: 'BBBBBB',
          engineCapacity: 'BBBBBB',
          fuelTankCapacity: 'BBBBBB',
          wheelBase: 'BBBBBB',
          power: 'BBBBBB',
          gvwWeight: 'BBBBBB',
          payloadWeight: 'BBBBBB',
          exShowRoomPrice: 1,
          onRoadPrice: 1,
          lob: 'BBBBBB',
          ppl: 'BBBBBB',
          pl: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          oppurtunityCreatedDate: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a OppurtunityDetails', () => {
      const patchObject = Object.assign(
        {
          bdmID: 'BBBBBB',
          accountSite: 'BBBBBB',
          engineCapacity: 'BBBBBB',
          fuelTankCapacity: 'BBBBBB',
          power: 'BBBBBB',
          exShowRoomPrice: 1,
          ppl: 'BBBBBB',
          pl: 'BBBBBB',
        },
        new OppurtunityDetails()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          oppurtunityCreatedDate: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of OppurtunityDetails', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          crmOppurtunityID: 'BBBBBB',
          oppurtunityCreatedDate: currentDate.format(DATE_FORMAT),
          crmCustomerID: 1,
          bdmName: 'BBBBBB',
          bdmID: 'BBBBBB',
          dseID: 'BBBBBB',
          dseName: 'BBBBBB',
          accountType: 'BBBBBB',
          accountName: 'BBBBBB',
          accountSite: 'BBBBBB',
          vehicleClass: 'BBBBBB',
          vehicleVariant: 'BBBBBB',
          engineCapacity: 'BBBBBB',
          fuelTankCapacity: 'BBBBBB',
          wheelBase: 'BBBBBB',
          power: 'BBBBBB',
          gvwWeight: 'BBBBBB',
          payloadWeight: 'BBBBBB',
          exShowRoomPrice: 1,
          onRoadPrice: 1,
          lob: 'BBBBBB',
          ppl: 'BBBBBB',
          pl: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          oppurtunityCreatedDate: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a OppurtunityDetails', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addOppurtunityDetailsToCollectionIfMissing', () => {
      it('should add a OppurtunityDetails to an empty array', () => {
        const oppurtunityDetails: IOppurtunityDetails = { id: 123 };
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing([], oppurtunityDetails);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(oppurtunityDetails);
      });

      it('should not add a OppurtunityDetails to an array that contains it', () => {
        const oppurtunityDetails: IOppurtunityDetails = { id: 123 };
        const oppurtunityDetailsCollection: IOppurtunityDetails[] = [
          {
            ...oppurtunityDetails,
          },
          { id: 456 },
        ];
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing(oppurtunityDetailsCollection, oppurtunityDetails);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a OppurtunityDetails to an array that doesn't contain it", () => {
        const oppurtunityDetails: IOppurtunityDetails = { id: 123 };
        const oppurtunityDetailsCollection: IOppurtunityDetails[] = [{ id: 456 }];
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing(oppurtunityDetailsCollection, oppurtunityDetails);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(oppurtunityDetails);
      });

      it('should add only unique OppurtunityDetails to an array', () => {
        const oppurtunityDetailsArray: IOppurtunityDetails[] = [{ id: 123 }, { id: 456 }, { id: 22258 }];
        const oppurtunityDetailsCollection: IOppurtunityDetails[] = [{ id: 123 }];
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing(oppurtunityDetailsCollection, ...oppurtunityDetailsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const oppurtunityDetails: IOppurtunityDetails = { id: 123 };
        const oppurtunityDetails2: IOppurtunityDetails = { id: 456 };
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing([], oppurtunityDetails, oppurtunityDetails2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(oppurtunityDetails);
        expect(expectedResult).toContain(oppurtunityDetails2);
      });

      it('should accept null and undefined values', () => {
        const oppurtunityDetails: IOppurtunityDetails = { id: 123 };
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing([], null, oppurtunityDetails, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(oppurtunityDetails);
      });

      it('should return initial array if no OppurtunityDetails is added', () => {
        const oppurtunityDetailsCollection: IOppurtunityDetails[] = [{ id: 123 }];
        expectedResult = service.addOppurtunityDetailsToCollectionIfMissing(oppurtunityDetailsCollection, undefined, null);
        expect(expectedResult).toEqual(oppurtunityDetailsCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
