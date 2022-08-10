import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SecurityService } from '../../service/security.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  email!: string;
  name!: string;
  surname!: string;
  role!: string;
  password!: string;
  confirmPassword!: string;
  message!: Observable<any>;

  constructor(private service: SecurityService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.signIn(this.email, this.name, this.surname, "USER", this.password, this.confirmPassword).subscribe(
      user => {
         this.router.navigate(['/login'])
       }
       )
 
  }

}
