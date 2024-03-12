/*
  Warnings:

  - You are about to alter the column `name` on the `Product` table. The data in that column could be lost. The data in that column will be cast from `Text` to `VarChar(255)`.
  - You are about to drop the column `userId` on the `Update` table. All the data in the column will be lost.
  - A unique constraint covering the columns `[id,belongsToId]` on the table `Product` will be added. If there are existing duplicate values, this will fail.

*/
-- AlterEnum
ALTER TYPE "UPDATE_STATUS" ADD VALUE 'SHIPPED';

-- DropForeignKey
ALTER TABLE "Update" DROP CONSTRAINT "Update_userId_fkey";

-- AlterTable
ALTER TABLE "Product" ALTER COLUMN "name" SET DATA TYPE VARCHAR(255);

-- AlterTable
ALTER TABLE "Update" DROP COLUMN "userId",
ALTER COLUMN "asset" DROP NOT NULL;

-- CreateIndex
CREATE UNIQUE INDEX "Product_id_belongsToId_key" ON "Product"("id", "belongsToId");
