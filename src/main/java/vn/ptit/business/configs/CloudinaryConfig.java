package vn.ptit.business.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
	public Cloudinary getCloudinary() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "cuongpham",
				  "api_key", "563118948926745",
				  "api_secret", "BXj88MsN5XQSBj8Y-CA5qUTVqr8"));
		return cloudinary;
	}
}
