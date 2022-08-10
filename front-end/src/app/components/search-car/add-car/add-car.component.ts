import { CarService } from '../../../service/car.service';
import { Router, RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {
  
  model: string | undefined;
  priceForADay: number | undefined;
  image: String | undefined;
  withInsurance: Boolean | undefined;
  withAirConditioner: Boolean | undefined;
  seats: String | undefined;
  transmission: String | undefined


  constructor(private service: CarService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.addCar(this.model, this.priceForADay, this.image,
      this.withInsurance, this.withAirConditioner, this.seats, this.transmission)
      .subscribe(car => {
        this.router.navigate(['/cars']);
      });
  }

}
