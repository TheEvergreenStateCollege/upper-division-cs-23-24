/*
  Warnings:

  - Added the required column `loofah` to the `UpdatePoint` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "UpdatePoint" ADD COLUMN     "loofah" INTEGER NOT NULL;
