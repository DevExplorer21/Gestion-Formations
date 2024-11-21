import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutOrganismeComponent } from './ajout-organisme.component';

describe('AjoutOrganismeComponent', () => {
  let component: AjoutOrganismeComponent;
  let fixture: ComponentFixture<AjoutOrganismeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AjoutOrganismeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AjoutOrganismeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
