package com.nemirovsky.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@Table(name = "TELEBOT_USER", schema = "IB_FILE_OPS_APP")
@NoArgsConstructor
@AllArgsConstructor
public class TelebotUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;
    @Column(name = "date_created")
    @JsonProperty("createDate")
    private LocalDateTime createDate;
    @Column(name = "date_updated")
    @JsonProperty("updateDate")
    private LocalDateTime updateDate;
    @Column(name = "user_name", nullable = false)
    @JsonProperty("userName")
    private String userName;
    @Column(name = "user_name_ext", nullable = false)
    @JsonProperty("userNameExt")
    private String userNameExt;
    @Column(name = "chat_id", unique = true, nullable = false)
    @JsonProperty("chatId")
    private long chatId;
    @Column(name = "status")
    @JsonProperty("status")
    private int status;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn
    private TelebotState state;

}
