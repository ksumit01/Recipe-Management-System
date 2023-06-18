CREATE TABLE `Recipe` (
  `recipe_id` int,
  `recipe_name` varchar(255),
  `ingredients` varchar(255),
  `preparation_steps` varchar(255),
  `created_at` date,
  `updated_at` date,
  `isDeleted` tinyint
);

CREATE TABLE `recipe_like` (
  `like_id` int,
  `recipe_id` int,
  `user_id` int,
  `created_at` date
);

CREATE TABLE `user` (
  `userId` int,
  `user_name` varchar(255),
  `user_email` varchar(255),
  `user_password` varchar(255),
  `created_at` date,
  `updated_at` date,
  `isDeleted` tinyint
);

ALTER TABLE `recipe_like` ADD FOREIGN KEY (`recipe_id`) REFERENCES `Recipe` (`recipe_id`);

ALTER TABLE `user` ADD FOREIGN KEY (`userId`) REFERENCES `recipe_like` (`user_id`);
