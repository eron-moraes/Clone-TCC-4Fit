import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthService } from "src/app/services/auth.service";

@Component({
  selector: "app-nav",
  templateUrl: "./nav.component.html",
  styleUrls: ["./nav.component.scss"],
})
export class NavComponent {
  constructor(
    private router: Router,
    private authService: AuthService,
    private toast: ToastrService
  ) {}

  // SignIn() {
  //   this.router.navigate(["login"]);
  //   this.authService.signIn();
  //   this.toast.info("Logout realizado com sucesso", "Logout");
  // }
}
