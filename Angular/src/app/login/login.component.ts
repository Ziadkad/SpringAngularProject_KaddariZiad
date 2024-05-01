import { Component } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  public loginForm! : FormGroup;

    constructor(private  fb: FormBuilder, private  authService :AuthService, private router:Router) {
    }
    ngOnInit():void {
      this.loginForm = this.fb.group({
        username : this.fb.control(""),
        password : this.fb.control(""),
      })
    }



  login():void{
    let username = this.loginForm.value.username;
    let password = this.loginForm.value.password;
    let auth:boolean = this.authService.login(username,password);
    if(auth){
      this.router.navigateByUrl("/admin")
    }
  }
}
