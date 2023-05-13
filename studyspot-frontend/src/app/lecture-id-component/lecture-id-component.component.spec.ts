import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LectureIdComponentComponent } from './lecture-id-component.component';

describe('LectureIdComponentComponent', () => {
  let component: LectureIdComponentComponent;
  let fixture: ComponentFixture<LectureIdComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LectureIdComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LectureIdComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
