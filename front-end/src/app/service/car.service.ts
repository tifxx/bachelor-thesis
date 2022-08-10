import { RouterModule } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Car } from './../model/Car';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http: HttpClient) { }

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>('/api/cars');
  }

  getCarWithId(id: number): Observable<Car> {
    return this.http.get<Car>(`/api/cars/${id}`);
  }

  addCar(model: String | undefined,
    priceForADay: number | undefined,
    image: String | undefined,
    withInsurance: Boolean | undefined,
    withAirConditioner: Boolean | undefined,
    seats: String | undefined,
    transmission: String | undefined): Observable<{}> {
    return this.http.post('/api/cars/addCar', {
      model: model,
      priceForADay: priceForADay,
      image: image,
      withInsurance: withInsurance,
      withAirConditioner: withAirConditioner,
      seats: seats,
      transmission: transmission
    });
  }

  sendDatesParameters(pickUpDate: string,
    dropOffDate: string): Observable<Car[]> {
      let params = new HttpParams().set("pickUpDate",pickUpDate).set("dropOffDate", dropOffDate); 
      return this.http.get<Car[]>('/api/cars/searchAvailable', {params:params});
  }

  getLocations() {
    return this.http.get<string[]>('/api/cars/locations');
  }

  updateCar(id: number, withInsurance: Boolean, withAirConditioner: Boolean, priceForADay: number) : Observable<{}>
  {
   return this.http.put(`/api/cars/edit/${id}`, { withInsurance: withInsurance,
    withAirConditioner: withAirConditioner,
     priceForADay: priceForADay})
  }
}
