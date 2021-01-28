package com.example.test.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TimeService")
@Getter
@Setter
public class TimeService {
    private static final long serialVersionUID = 11L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, length = 50)
    private int user_id;
    @Column(name = "serviceTimeStart", nullable = false, length = 256)
    private Date serviceTimeStart;
    @Column(name = "serviceTimeEnd", nullable = false, length = 256)
    private Date serviceTimeEnd ;
    @Column(name = "serviceName", nullable = false, length = 500)
    private String serviceName ;



}
