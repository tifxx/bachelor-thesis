import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from 'src/app/service/reservation.service';

@Component({
  selector: 'app-change-status',
  templateUrl: './change-status.component.html',
  styleUrls: ['./change-status.component.css']
})
export class ChangeStatusComponent implements OnInit {

  reservationId: number
  reservationStatus: string[] | undefined
  clickedStatus!: string

  constructor(private route: ActivatedRoute, private service: ReservationService, private router: Router) { 
    this.reservationId = +this.route.snapshot.paramMap.get('id')!
  }

  ngOnInit(): void {
    this.service.getReservationStatus().subscribe(status=>this.reservationStatus=status)
  }

  onSubmit() {
    this.service.changeStatus(this.reservationId, this.clickedStatus)
      .subscribe(r => {
        this.router.navigate(['/reservations']);
      })

  }
}
