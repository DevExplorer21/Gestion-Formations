import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
//import { paysComponent } from './components/pays/pays.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { PaysComponent } from '../pays/pays.component';
import { PaysService } from 'src/app/services/pays.service';

@Component({
  selector: 'app-ajout-pays',
  templateUrl: './ajout-pays.component.html',
  styleUrls: ['./ajout-pays.component.css']
})
export class AjoutPaysComponent implements OnInit {
  paysForm !: FormGroup;
  actionsBtn: String="Ajouter"
  constructor(private formBuilder: FormBuilder,
    private dialogref:MatDialogRef<PaysComponent>,
    @Inject(MAT_DIALOG_DATA) public editData:any,  
    private paysService: PaysService) { }

  ngOnInit(): void {
    this.paysForm=this.formBuilder.group({
      nom :['', Validators.required]
    });
    console.log(this.editData);
    if(this.editData)
    { this.actionsBtn="Modifier";
      this.paysForm.controls['nom'].setValue(this.editData.nom);
    }
  }

  addPays(){
    console.log(this.paysForm.value);
    if(!this.editData){
      if(this.paysForm.valid){
        this.paysService.create(this.paysForm.value)
        .subscribe({
          next:(res)=>{
            alert("Pays est ajouté avec succès");
            this.paysForm.reset();
            this.dialogref.close('save');
          },
          error:()=>{
            alert("Erreur dans la création de pays")
          }
        })
      }
    }else{
     this.updatePays();
    }
    }

    updatePays(){
      this.paysService.update(this.editData.id, this.paysForm.value)
      .subscribe({
        next:(res)=>{
          alert("pays est modifiée avec succès");
          this.paysForm.reset();
          this.dialogref.close('update');
        },
        error:()=>{
          alert("Erreur dans la modification de pays");
        }
      })
    }

}
