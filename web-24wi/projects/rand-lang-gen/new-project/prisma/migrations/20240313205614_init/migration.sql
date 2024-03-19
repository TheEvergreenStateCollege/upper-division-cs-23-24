/*
  Warnings:

  - The primary key for the `User` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The `id` column on the `User` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - The primary key for the `Word` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `belongsToId` on the `Word` table. All the data in the column will be lost.
  - The `id` column on the `Word` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - You are about to alter the column `name` on the `Word` table. The data in that column could be lost. The data in that column will be cast from `Text` to `VarChar(255)`.
  - You are about to drop the `Update` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `UpdatePoint` table. If the table is not empty, all the data it contains will be lost.
  - Added the required column `authorId` to the `Word` table without a default value. This is not possible if the table is not empty.
  - Added the required column `define` to the `Word` table without a default value. This is not possible if the table is not empty.
  - Added the required column `language` to the `Word` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "Update" DROP CONSTRAINT "Update_userId_fkey";

-- DropForeignKey
ALTER TABLE "Update" DROP CONSTRAINT "Update_wordId_fkey";

-- DropForeignKey
ALTER TABLE "UpdatePoint" DROP CONSTRAINT "UpdatePoint_updateId_fkey";

-- DropForeignKey
ALTER TABLE "Word" DROP CONSTRAINT "Word_belongsToId_fkey";

-- AlterTable
ALTER TABLE "User" DROP CONSTRAINT "User_pkey",
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
ADD CONSTRAINT "User_pkey" PRIMARY KEY ("id");

-- AlterTable
ALTER TABLE "Word" DROP CONSTRAINT "Word_pkey",
DROP COLUMN "belongsToId",
ADD COLUMN     "authorId" INTEGER NOT NULL,
ADD COLUMN     "define" TEXT NOT NULL,
ADD COLUMN     "language" TEXT NOT NULL,
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
ALTER COLUMN "name" SET DATA TYPE VARCHAR(255),
ADD CONSTRAINT "Word_pkey" PRIMARY KEY ("id");

-- DropTable
DROP TABLE "Update";

-- DropTable
DROP TABLE "UpdatePoint";

-- DropEnum
DROP TYPE "UPDATE_STATUS";

-- AddForeignKey
ALTER TABLE "Word" ADD CONSTRAINT "Word_authorId_fkey" FOREIGN KEY ("authorId") REFERENCES "User"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
