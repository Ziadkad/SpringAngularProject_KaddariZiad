import { Injectable } from '@angular/core';
import {Route, Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public users:any ={
    admin : {password:'1234', role:['STUDENT','ADMIN']},
    user1 : {password:'1234', role:['STUDENT']}
  }

  public usernameP : any;
  public isAuthentificated : boolean = false;
  public  roles:string[] =[]

  constructor(private router: Router) { }

  public login(username:string, password:string) :boolean{
      if(this.users[username] && this.users[username]['password'] == password){
        this.usernameP = username;
        this.isAuthentificated=   true;
        this.roles = this.users[username]['role'];
        return true;
      }
      else {
        return false;
      }
  }
  public logout(){
    this.isAuthentificated=false;
    this.roles=[];
    this.usernameP = undefined;
    this.router.navigateByUrl('/login')
  }
}
