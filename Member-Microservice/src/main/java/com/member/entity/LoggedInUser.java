package com.member.entity;

public class LoggedInUser {

        private Integer userId;
		
		private String email;
		
		private Integer memberId;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}


		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
		
		
}
