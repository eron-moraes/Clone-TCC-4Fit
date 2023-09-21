import { Component, Inject } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material/dialog";
import { ToastrService } from "ngx-toastr";
import { Operacao } from "src/app/enums/operacao-enum";
import { Aluno } from "src/app/models/aluno";
import { AlunoService } from "src/app/services/aluno.service";
import { NotificationService } from "../../../services/notification.service";

@Component({
  selector: "app-aluno-crud",
  templateUrl: "./aluno-crud.component.html",
  styleUrls: ["./aluno-crud.component.scss"],
})
export class AlunoCrudComponent {
  aluno: Aluno = new Aluno();
  form: FormGroup;
  operacao: string = "";

  constructor(
    private service: AlunoService,
    private toast: ToastrService,
    private notification: NotificationService,
    public dialogRef: MatDialogRef<AlunoCrudComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if (data) {
      this.aluno = data.element;
      console.log(this.aluno);

      this.operacao = data.operacao;
    }
    this.form = new FormGroup({
      nome: new FormControl(
        {
          value: this.aluno?.nome ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.minLength(3)
      ),
      cpf: new FormControl(
        {
          value: this.aluno?.cpf ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.required
      ),
      email: new FormControl(
        {
          value: this.aluno?.email ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.email
      ),
      telefone: new FormControl(
        {
          value: this.aluno?.telefone ?? null,
          disabled: this.operacao == "Deletar",
        },
        Validators.minLength(3)
      ),
      dataNascimento: new FormControl({
        value: this.aluno?.dataNascimeno ?? null,
        disabled: this.operacao == "Deletar",
      }),
    });
  }

  create(): void {
    this.service.create(this.form.value).subscribe(
      () => {
        this.notification.enviarNotificacao(
          "Tudo certo",
          "Aluno cadastrado com sucesso",
          "success"
        );
        // this.toast.success("Aluno cadastrado com sucesso", "Cadastro");
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
          "Tem certeza que deseja excluir esse aluno?",
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
    this.service.delete(this.aluno.id).subscribe(
      (res) => {
        debugger;
      },
      (e) => {
        if (e.status == 200) {
          this.dialogRef.close();
          this.notification.enviarNotificacaoToRoute(
            "Tudo certo",
            "Aluno excluído com sucesso",
            "success",
            "/aluno"
          );
        } else {
          this.notification.enviarNotificacao(
            "Ops",
            "Falha ao excluir aluno!",
            "error"
          );
        }
      }
    );
  }
  atualizar() {
    let aluno: Aluno = Object.assign(this.aluno, this.form.value);
    this.service.update(aluno).subscribe((res) => {
      this.notification.enviarNotificacao(
        "Tudo certo!",
        "Aluno atualizado com sucesso!",
        "success"
      );
      this.dialogRef.close();
    });
  }
}
