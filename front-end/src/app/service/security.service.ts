import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { Response } from '../model/Response';



@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(private http: HttpClient) { }

  signIn(email: string, name: string, surname: string, role: string, password: string, confirmPassword: string): Observable<Response<string>> {
    return this.http.post<Response<string>>('/api/auth/signin', {
      email: email,
      name: name,
      surname: surname,
      role: role,
      password: password,
      confirmPassword: confirmPassword
    })
  }

  login(email: string, password: string): Observable<User> {
    return this.http.post<User>('/api/auth/login', {
      email: email,
      password: password
    })
  }


}
