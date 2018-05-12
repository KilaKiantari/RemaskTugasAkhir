package com.example.asus_desktop.remask.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Asus-Desktop on 5/6/2018.
 */

public class ModelLoginUser
{
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("result")
        @Expose
        private Result result;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

    }


