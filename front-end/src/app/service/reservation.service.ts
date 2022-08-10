import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../model/Reservation';
import { User } from '../model/User';
import { Review } from '../model/Review';
import { TokenService } from './token.service';


@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient, private token: TokenService) { }

  addNewReservation(pickUpLocation: string, dropOffLocation: string, totalPrice: number,
    carId: number, clientId: number, pickUpDate: string, dropOffDate: string): Observable<{}> {
    return this.http.post("/api/reservations/add", {
      pickUpLocationId: pickUpLocation,
      dropOffLocationId: dropOffLocation, totalPrice: totalPrice,
      carId: carId, clientId: clientId, pickUpDate: pickUpDate, dropOffDate: dropOffDate
    })
  }

  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>("/api/reservations/all")
  }

  getReservationsByUser(user: User): Observable<Reservation[]> {
    let params = new HttpParams().set("id", user.id)
    return this.http.get<Reservation[]>("/api/reservations/byUser", { params: params })
  }

  getReservationStatus(): Observable<string[]> {
    return this.http.get<string[]>("/api/reservations/status")
  }

  changeStatus(id: number, clickedStatus: string): Observable<{}> {
    return this.http.put(`/api/reservations/changeStatus/${id}`, { reservationStatus: clickedStatus })
  }

  addNewReview(resId: Number, carId: Number, clientId: Number, rating: Number): Observable<{}> {
    return this.http.post('/api/reservations/addReview', {
      reservationId: resId,
      carId: carId,
      clientId: clientId,
      rating: rating,
    })
  }

  viewPdf(id: Number): Observable<Blob> {
    return this.http.get<Blob>(`/api/reservations/pdf/${id}`, { responseType: 'blob' as 'json'})
  }

  cancelReservation(id: Number): Observable<{}> {
    return this.http.delete(`/api/reservations/cancel/${id}`)
  }
}

