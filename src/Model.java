
public class Model {
        private String tentk;
        private String matkhau;
        private String loaitk;
        private String ngaythem;
        private String truycap;
        public Model() {
			super();
		}
		public Model(String tentk, String matkhau, String loaitk, String ngaythem, String truycap) {
			super();
			this.tentk = tentk;
			this.matkhau = matkhau;
			this.loaitk = loaitk;
			this.ngaythem = ngaythem;
			this.truycap = truycap;
		}
		public String getTentk() {
			return tentk;
		}
		public void setTentk(String tentk) {
			this.tentk = tentk;
		}
		public String getMatkhau() {
			return matkhau;
		}
		public void setMatkhau(String matkhau) {
			this.matkhau = matkhau;
		}
		public String getLoaitk() {
			return loaitk;
		}
		public void setLoaitk(String loaitk) {
			this.loaitk = loaitk;
		}
		public String getNgaythem() {
			return ngaythem;
		}
		public void setNgaythem(String ngaythem) {
			this.ngaythem = ngaythem;
		}
		public String getTruycap() {
			return truycap;
		}
		public void setTruycap(String truycap) {
			this.truycap = truycap;
		} 
}
