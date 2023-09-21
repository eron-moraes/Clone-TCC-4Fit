import { Injectable } from "@angular/core";
import Swal from "sweetalert2";
import { Router } from "@angular/router";

@Injectable({ providedIn: "root" })
export class NotificationService {
  constructor(private route: Router) {}

  public enviarNotificacao(titulo: string, texto: any, tipo: any): void {
    texto =
      texto instanceof Object
        ? "Ocorreu um erro inesperado"
        : texto.replace("undefined.", "");
    Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo,
      confirmButtonText: "Ok",
      confirmButtonColor: "#c3c3c3",
    });
  }

  public enviarNotificacaoConfirmar(
    titulo: string,
    texto: string,
    tipo: any
  ): Promise<any> {
    return Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo,
      showCancelButton: true,
      confirmButtonText: "Confirmar",
      confirmButtonColor: "#DC3333",
      cancelButtonText: "Cancelar",
      cancelButtonColor: "#c3c3c3",
    });
  }

  public enviarNotificacaoBoolean(
    titulo: string,
    texto: string,
    tipo: any
  ): Promise<any> {
    return Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo,
      showCancelButton: true,
      confirmButtonText: "Sim",
      confirmButtonColor: "#54246b",
      cancelButtonText: "Não",
      cancelButtonColor: "#c3c3c3",
      showCloseButton: true,
    });
  }

  public enviarNotificacaoAtualizarPagina(
    titulo: string,
    texto: string,
    tipo: any
  ): void {
    Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo,
      confirmButtonColor: "#c3c3c3",
      confirmButtonText: "OK",
    }).then((result) => {
      if (result.value) {
        window.location.reload();
      }
    });
  }

  public enviarNotificacaoToRoute(
    titulo: string,
    texto: string,
    tipo: any,
    rota: string
  ): void {
    Swal.fire({
      title: titulo,
      text: texto,
      icon: tipo,
      confirmButtonColor: "#c3c3c3",
      confirmButtonText: "OK",
    }).then((result) => {
      if (result.value) {
        this.route.navigate([`${rota}`]);
      }
    });
  }

  public enviarNotificacaoHtml(titulo: string, texto: string, tipo: any): void {
    texto = texto.replace("undefined.", "");
    Swal.fire({
      title: titulo,
      html: texto,
      icon: tipo,
      confirmButtonText: "Ok",
      confirmButtonColor: "#c3c3c3",
    });
  }
}
