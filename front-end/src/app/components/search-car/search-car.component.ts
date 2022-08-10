import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Car } from 'src/app/model/Car';
import { CarService } from 'src/app/service/car.service';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.css']
})
export class SearchCarComponent implements OnInit {

  pickUpDate!: string
  dropOffDate!: string
  cars!: Car[]
  navigationExtra: NavigationExtras | undefined
  todayDate: string

  constructor(private service: CarService, private router: Router) {
    this.todayDate = new Date().toISOString().slice(0,16)
  }

  ngOnInit(): void {

  }

  onSubmit() {
    this.service.sendDatesParameters(this.pickUpDate,
      this.dropOffDate)
      .subscribe( car => {
        this.cars = car,
          this.navigationExtra = {
            state: {
              cars: this.cars,
              pickUpDate: this.pickUpDate,
              dropOffDate: this.dropOffDate
            }
          }
        this.router.navigate(['/cars'], this.navigationExtra);
      })
  }

}
