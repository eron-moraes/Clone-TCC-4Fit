import { Component } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Credenciais } from "src/app/models/credenciais";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent {
  creds: Credenciais = {
    email: "",
    senha: "",
  };
  form: FormGroup;

  constructor(public router: Router, public authService: AuthService) {
    this.form = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      senha: new FormControl(null, [
        Validators.required,
        Validators.minLength(8),
      ]),
    });
  }
  validateInput(name: string): boolean {
    const control = this.form.controls[name];
    if (control) {
      return (
        (control.errors ? control.errors["required"] : false) &&
        (control.dirty || control.touched)
      );
    }
    return false;
  }

  // logar() {
  //   if (this.form.invalid) {
  //     return this.form.markAllAsTouched();
  //   }
  //   this.router.navigate(["inicio"]);
  // }
}
