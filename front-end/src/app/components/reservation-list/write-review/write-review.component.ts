import { TokenService } from 'src/app/service/token.service';
import { ReservationService } from 'src/app/service/reservation.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-write-review',
  templateUrl: './write-review.component.html',
  styleUrls: ['./write-review.component.css']
})
export class WriteReviewComponent implements OnInit {

  rating: number = 3;
  starCount: number = 5;
  clientId!: number 

  constructor(private reservationService: ReservationService, private route: ActivatedRoute,
    private router: Router, private tokenService: TokenService) {
      this.clientId = this.tokenService.getUser()!.id
  }

  ngOnInit(): void {
  }

  onRatingChanged(rating: number) {
    this.rating = rating
  }

  onSubmit() {
    this.reservationService.addNewReview(+this.route.snapshot.paramMap.get('resId')!,+this.route.snapshot.paramMap.get('carId')!, this.clientId, this.rating)
      .subscribe(r => this.router.navigate(['/reservations']))
  }
}
