import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: User | undefined

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenService.getUser()
  }

}
