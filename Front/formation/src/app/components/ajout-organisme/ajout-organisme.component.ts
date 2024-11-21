import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
//import { DomainComponent } from './components/domain/domain.component';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DomainComponent } from '../domain/domain.component';
import { OrganismeComponent } from '../organisme/organisme.component';
import { OrganismeService } from 'src/app/services/organisme.service';

@Component({
  selector: 'app-ajout-organisme',
  templateUrl: './ajout-organisme.component.html',
  styleUrls: ['./ajout-organisme.component.css']
})
export class AjoutOrganismeComponent implements OnInit {

  organismeForm !: FormGroup;
  actionsBtn: String = "Ajouter"
  constructor(private formBuilder: FormBuilder,
    private dialogref: MatDialogRef<OrganismeComponent>,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    private organismeService: OrganismeService) { }

  ngOnInit(): void {
    this.organismeForm = this.formBuilder.group({
      libelle: ['', Validators.required]
    });
    console.log(this.editData);
    if (this.editData) {
      this.actionsBtn = "Modifier";
      this.organismeForm.controls['libelle'].setValue(this.editData.libelle);
    }
  }

  addOrganisme() {
    console.log(this.organismeForm.value);
    if (!this.editData) {
      if (this.organismeForm.valid) {
        this.organismeService.create(this.organismeForm.value)
          .subscribe({
            next: (res) => {
              alert("Organisme est ajouté avec succès");
              this.organismeForm.reset();
              this.dialogref.close('save');
            },
            error: () => {
              alert("Erreur dans la création de l'organisme")
            }
          })
      }
    } else {
      this.updateOrganisme();
    }
  }

  updateOrganisme() {
    this.organismeService.update(this.editData.id, this.organismeForm.value)
      .subscribe({
        next: (res) => {
          alert("Organisme est modifiée avec succès");
          this.organismeForm.reset();
          this.dialogref.close('update');
        },
        error: () => {
          alert("Erreur dans la modification de l'organisme");
        }
      })
  }


}
