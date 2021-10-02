/**
 * @author: Alan Benavides
 */

package com.spring.innovation.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ItemInventoryEntry extends ModelBase {
    @ManyToOne
    private ItemInventory itemInventory;
    @Enumerated(EnumType.STRING)
    private MovementType movementType;
    private BigDecimal quantity; // represent sale or buy instances quantity

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
