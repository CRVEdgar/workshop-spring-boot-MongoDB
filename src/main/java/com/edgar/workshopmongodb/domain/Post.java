package com.edgar.workshopmongodb.domain;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date data;
    private String title;
    private String body;


}
