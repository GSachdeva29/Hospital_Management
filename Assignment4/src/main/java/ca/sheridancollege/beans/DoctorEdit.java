package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorEdit implements Serializable
{
  private int pId;
  private String vDate;
  private String vReason;
  private String vFeedback;
  private String vPrescription;
  private String vNext;
}
