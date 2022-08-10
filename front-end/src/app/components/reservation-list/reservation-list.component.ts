import { ILocation } from 'src/app/model/ILocation';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/model/Reservation';
import { User } from 'src/app/model/User';
import { ReservationService } from 'src/app/service/reservation.service';
import { TokenService } from 'src/app/service/token.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservation: Reservation[] | undefined
  user: User | undefined
  role: boolean | undefined
  pickUpLocation: ILocation | undefined

  constructor(private token: TokenService, private service: ReservationService, private router: Router) { }

  ngOnInit(): void {
    this.user = this.token.getUser()
    if (this.user != undefined && this.user.role == 'USER') {
      this.service.getReservationsByUser(this.user).subscribe(reservations => this.reservation = reservations)
    }
    else if (this.user != undefined && this.user.role == 'ADMIN') {
      this.service.getAllReservations().subscribe(reservations => this.reservation = reservations)
    }
  }

  cancel(resId: number) {
    this.service.cancelReservation(resId).subscribe()
    window.location.href = '/reservations';
  }

  viewPdf(resId: number) {
    this.service.viewPdf(resId)
    .subscribe((blob: Blob): void => {
      const file = new Blob([blob], {type: 'application/pdf'});
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL, '_blank', 'width=1000, height=800');
    });
  }

}
