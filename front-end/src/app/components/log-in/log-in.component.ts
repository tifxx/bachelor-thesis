import { TokenService } from './../../service/token.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../../service/security.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  email!: string;
  password!: string;


  constructor(private service: SecurityService, private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.service.login(this.email, this.password.toString()).subscribe({
      next: user => {
        console.log(user)
        this.tokenService.saveToken(user.token)
        this.tokenService.saveUser(user)
        window.location.href = '/home';
      }
    })
  }
}