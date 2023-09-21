import { Component, Inject } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ToastrService } from "ngx-toastr";
import { Operacao } from "src/app/enums/operacao-enum";
import { Instrutor } from "src/app/models/instrutor";
import { InstrutorService } from "src/app/services/instrutor.service";
import { NotificationService } from "../../../services/notification.service";

@Component({
  selector: "app-instrutor-crud",
  templateUrl: "./instrutor-crud.component.html",
  styleUrls: ["./instrutor-crud.component.scss"],
})
export class InstrutorCrudComponent {
  instrutor: Instrutor = new Instrutor();
  form: FormGroup;
  operacao: string = "";

  constructor(
    private service: InstrutorService,
    private toast: ToastrService,
    private notification: NotificationService,
    public dialogRef: MatDialogRef<InstrutorCrudComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data) {
      this.instrutor = data.element;
      this.operacao = data.operacao;
    }
    this.form = new FormGroup({
      nome: new FormControl(
        {
          value: this.instrutor?.nome ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.minLength(3)
      ),
      cpf: new FormControl(
        {
          value: this.instrutor?.cpf ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.required
      ),
      email: new FormControl(
        {
          value: this.instrutor?.email ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.email
      ),
      telefone: new FormControl(
        {
          value: this.instrutor?.telefone ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.minLength(3)
      ),
      dataNascimento: new FormControl({
        value: this.instrutor?.dataNascimeno ?? null,
        disabled: this.operacao == "Deletar",
      }),
    });
  }

  create(): void {
    this.service.create(this.form.value).subscribe(
      () => {
        this.notification.enviarNotificacao(
          "Tudo certo",
          "Instrutor cadastrado com sucesso",
          "success"
        );
        this.dialogRef.close();
      },
      (ex) => {
        if (ex.error.errors) {
          ex.error.errors.forEach((element) => {
            this.toast.error(element.message);
          });
        } else {
          this.toast.error(ex.error.message);
        }
      }
    );
  }

  cancelar() {
    this.dialogRef.close();
  }
  concluir() {
    if (this.operacao == Operacao.Deletar) {
      this.notification
        .enviarNotificacaoConfirmar(
          "Excluir",
          "Tem certeza que deseja excluir esse instrutor?",
          "warn"
        )
        .then((x) => {
          if (x.isConfirmed) {
            return this.deletar();
          }
        });
    } else if (this.operacao == Operacao.Cadastrar) {
      if (this.form.invalid) {
        this.form.markAllAsTouched();
        return;
      }
      return this.create();
    } else {
      if (this.form.invalid) {
        this.form.markAllAsTouched();
        return;
      }
      return this.atualizar();
    }
  }
  deletar() {
    this.service.delete(this.instrutor.id).subscribe(
      (res) => {
        debugger;
      },
      (e) => {
        if (e.status == 200) {
          this.dialogRef.close();
          this.notification.enviarNotificacaoToRoute(
            "Tudo certo",
            "Instrutor excluído com sucesso",
            "success",
            "/instrutor"
          );
        } else {
          this.notification.enviarNotificacao(
            "Ops",
            "Falha ao excluir instrutor!",
            "error"
          );
        }
      }
    );
  }
  atualizar() {
    let instrutor: Instrutor = Object.assign(this.instrutor, this.form.value);
    this.service.update(instrutor).subscribe((res) => {
      this.notification.enviarNotificacao(
        "Tudo certo!",
        "Instrutor atualizado com sucesso!",
        "success"
      );
      this.dialogRef.close();
    });
  }
}
